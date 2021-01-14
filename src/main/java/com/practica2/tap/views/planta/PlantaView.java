package com.practica2.tap.views.planta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.practica2.tap.logic.Building;
import com.practica2.tap.logic.Elevator;
import com.practica2.tap.logic.Floor;
import com.practica2.tap.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "plantas", layout = MainView.class)
@PageTitle("Plantas")
public class PlantaView extends VerticalLayout{
	
	private Building edificio;
	private Notification notification;
	
	public PlantaView() {
		
		edificio = Building.getInstance(7, 3);
		
		notification = new Notification();
		notification.setDuration(3000);
		
		Tabs tabs = new Tabs();
		Div pages = new Div();
		Map<Tab, Component> tabsToPages = new HashMap<>();
		
		for (int i = 0; i < 7; i++) {
			Tab tab = new Tab("Planta " + i);
			Div page = new Div();
			page.add(plantaLayout(edificio.getFloors().get(i), edificio.getElevators()));
			
			if(i!=1) page.setVisible(false);
			
			tabs.add(tab);
			pages.add(page);
			tabsToPages.put(tab, page);
		}			
		
		tabs.addSelectedChangeListener(event ->{
			tabsToPages.values().forEach(page -> page.setVisible(false));
			Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
			selectedPage.setVisible(true);
		});
						
		this.setHorizontalComponentAlignment(Alignment.CENTER, pages);
		add(tabs, pages);
	}
	
	public Component plantaLayout(Floor planta, ArrayList<Elevator> ascensores) {
		
		FormLayout plantaLayout = new FormLayout();		
		plantaLayout.setResponsiveSteps(new ResponsiveStep("20em", 3));
						
		TextField pantallaAscensor1 = new TextField("Planta");
		ascensores.get(0).attachObserver(pantallaAscensor1);
		pantallaAscensor1.setReadOnly(true);
		
		TextField pantallaAscensor2 = new TextField("Planta");
		ascensores.get(1).attachObserver(pantallaAscensor2);
		pantallaAscensor2.setReadOnly(true);
		
		TextField pantallaAscensor3 = new TextField("Planta");
		ascensores.get(2).attachObserver(pantallaAscensor3);
		pantallaAscensor3.setReadOnly(true);
		
		Button botonAscensor1 = new Button("Llamar");
		Button botonAscensor2 = new Button("Llamar");
		Button botonAscensor3 = new Button("Llamar");
		
		
		botonAscensor1.addClickListener(e -> {
			notification.setText(planta.llamarAscensor(ascensores.get(0)));
			notification.open();
		});
		
		botonAscensor2.addClickListener(e -> {
			notification.setText(planta.llamarAscensor(ascensores.get(1)));
			notification.open();
			
		});
		
		botonAscensor3.addClickListener(e -> {
			notification.setText(planta.llamarAscensor(ascensores.get(2)));
			notification.open();			
		});
		
		plantaLayout.add(pantallaAscensor1, pantallaAscensor2, pantallaAscensor3);
		plantaLayout.add(botonAscensor1, botonAscensor2, botonAscensor3);		
		plantaLayout.setWidth("70%");		
		
		return plantaLayout;		
	}
	
}
