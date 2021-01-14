package com.practica2.tap.views.ascensor;

import java.util.HashMap;
import java.util.Map;

import com.practica2.tap.logic.Building;
import com.practica2.tap.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
@PageTitle("Ascensores")
public class AscensorView extends VerticalLayout{
	
	private Building edificio;
	private Notification notification;
	
	public AscensorView() {			
		
		edificio = Building.getInstance(7, 3);
		
		notification = new Notification();
		notification.setDuration(3000);
		
		Tabs tabs = new Tabs(); //Contendrá todos los tabs de la vista
		Div pages = new Div(); //Contendrá todos los divs de la vista
		Map<Tab, Component> tabsToPages = new HashMap<>(); //Para relacionar cada tab con cada page
		
		for(int i = 0; i < 3; i++) {
			Tab tab = new Tab("Ascensor " + i);
			Div page = new Div();
			page.add(panelAscensor(i));			
			
			if(i!=1) page.setVisible(false); //Inicialmente se visualiza solo el primer panel
						
			tabs.add(tab);
			pages.add(page);
			tabsToPages.put(tab, page);
		}
		
		tabs.addSelectedChangeListener(event ->{
			tabsToPages.values().forEach(page -> page.setVisible(false)); //Se quita la visibilidad de todas las páginas
			Component selectedPage = tabsToPages.get(tabs.getSelectedTab()); //Se obtiene el tab selecionado
			selectedPage.setVisible(true); //Se pone visible el tab seleccionado
		});		

		this.setHorizontalComponentAlignment(Alignment.CENTER, pages);
		add(tabs, pages);
	}
	
	public VerticalLayout panelAscensor(int ascensor) {
		
		TextField pantallaPlanta = new TextField();
		edificio.getElevators().get(ascensor).attachObserver(pantallaPlanta);
		pantallaPlanta.setReadOnly(true);
		pantallaPlanta.setLabel("Planta");		
		pantallaPlanta.setWidth("90%");		
		
		NumberField pisoDestino = new NumberField();
		pisoDestino.setValue(1d);
		pisoDestino.setHasControls(true);		
		pisoDestino.setMin(0);		
		pisoDestino.setMax(6);
		pisoDestino.setWidthFull();
		
		Button botonConfirmar = new Button("Ok");
		botonConfirmar.setWidthFull();
		Button botonAbrirCerrar = new Button("Abir/Cerrar");
		botonAbrirCerrar.setWidthFull();
		Button botonEmergencia = new Button("Emergencia");
		botonEmergencia.setWidthFull();		
		
		botonConfirmar.addClickListener(e -> {
			int destino = (int)Math.round(pisoDestino.getValue());			
			notification.setText(edificio.getElevators().get(ascensor).select_floor(destino));
			notification.open();
		});
		
		botonAbrirCerrar.addClickListener(e -> {			
			notification.setText(edificio.getElevators().get(ascensor).doors_button());
			notification.open();
		});
		
		botonEmergencia.addClickListener(e -> {
			notification.setText(edificio.getElevators().get(ascensor).emergency_button());
			notification.open();
		});
		
		VerticalLayout col1 = new VerticalLayout(pisoDestino, botonAbrirCerrar);
		col1.setWidth("50%");
		
		VerticalLayout col2 = new VerticalLayout(botonConfirmar, botonEmergencia);
		col2.setWidth("50%");
		
		VerticalLayout panelAscensor = new VerticalLayout();

		panelAscensor.setHorizontalComponentAlignment(Alignment.CENTER, pantallaPlanta);
		panelAscensor.getStyle().set("border", "1px solid #9E9E9E");			
		panelAscensor.add(pantallaPlanta, new HorizontalLayout(col1, col2));				
		
		return panelAscensor;
	}
}
