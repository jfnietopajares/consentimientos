package com.jnieto.consentimientos;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

	public class VerPdf extends  VerticalLayout {

		public VerPdf(String consentimiento) {
			this.setSizeFull();
			this.setHeightUndefined();
		//	this.setWidth("600px");
		//	this.setHeight("600px");
			File pdfFile = new File("/Users/JuanNieto/Downloads/TELEOFT.pdf");
			Embedded pdf = new Embedded("", new FileResource(pdfFile));
			pdf.setMimeType("application/pdf");
			pdf.setType(Embedded.TYPE_BROWSER);
			pdf.setHeightUndefined();
			pdf.setWidth("400px");
			pdf.setHeight("300px");
			pdf.setSizeFull();
			this.addComponent(pdf);
		this.setHeightUndefined();
		}

	}


	
