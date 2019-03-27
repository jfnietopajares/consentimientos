package con.jnieto.ui;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jnieto.consentimientos.VerPdf;
import com.vaadin.server.FileResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.VerticalLayout;

public class PantallaSeleccion extends HorizontalLayout {

	public List<String> servicios = Arrays.asList("Ginecología", "Obtetricia");

	public List<String> idiomas = Arrays.asList("Árabe", "Chino", "Francés", "Inglés", "Polaco", "Rumano");

	public List<String> cgines = Arrays.asList("Áradfadaabe", "asdfad", "adfaafd", "asd", "adfad", "asdfasdf");

	public List<String> cosbtes = Arrays.asList("Áradadsfadffadaabe", "asdasdfadffad", "adfaasdfasdafd", "sdfasd",
			"asdf", "asdf");

	public RadioButtonGroup<String> servicio = new RadioButtonGroup<>("Servicio", servicios);

	public RadioButtonGroup<String> idioma = new RadioButtonGroup<>("Idioma", idiomas);

	private RadioButtonGroup<String> cgine = new RadioButtonGroup<>("C.Gine", cgines);
	private RadioButtonGroup<String> cosbte = new RadioButtonGroup<>("C.Obs", cosbtes);

	private HorizontalLayout fila1 = new HorizontalLayout();
	private HorizontalLayout fila2 = new HorizontalLayout();

	private VerticalLayout col1 = new VerticalLayout();
	private VerticalLayout col2 = new VerticalLayout();
	private VerticalLayout col3 = new VerticalLayout();

	public PantallaSeleccion() {
		this.setMargin(false);
		// this.setSizeFull();
		this.setCaption("Consentimientos.");
		servicio.addValueChangeListener(event -> clickServicio());
	//	servicio.addContextClickListener((event -> clickServicio())
	//	servicio.setSelectedItem("Ginecología");
		
		//idioma.setSelectedItem("Árabe");
		idioma.addValueChangeListener(event -> clickIdioma());

		fila1.setMargin(false);
		fila2.setMargin(false);

		col1.setMargin(false);
		col1.setWidth("110px");
		col2.setMargin(false);
		col2.setWidth("200px");
		// col3.setSizeFull();
		col3.setMargin(false);
		col3.setWidth("600px");
		col3.setHeightUndefined();

		fila1.addComponent(servicio);
		fila2.addComponent(idioma);
		cgine.setVisible(false);
		cgine.addValueChangeListener(event -> clickVerPdfG());
		cosbte.setVisible(false);
		cosbte.addValueChangeListener(event -> clickVerPdfO());

		col1.addComponents(fila1, fila2);
		col2.addComponents(cgine, cosbte);

		this.addComponents(col1, col2, col3);

	}

	public void clickIdioma() {
		if (servicio.getValue()==null) {
			Notification.show("Elige servicio " , Type.ASSISTIVE_NOTIFICATION);
		} else if ( idioma.getValue()==null) {
			Notification.show("Elige idioma " , Type.ASSISTIVE_NOTIFICATION);
		} else {
			if (cgine.isVisible() && cgine.getValue()!=null) {
				col3.removeAllComponents();
				col3.addComponent(verPdf(cgine.getValue(), "ginecologia", idioma.getValue()));
			}
			if (cosbte.isVisible() && cosbte.getValue()!=null) {
				col3.removeAllComponents();
				col3.addComponent(verPdf(cgine.getValue(), "ginecologia", idioma.getValue()));
			}
		}
	}
	
	public void clickVerPdfG() {
		if (cgine.getValue()==null) {
			Notification.show("Elige consentimiento " , Type.ASSISTIVE_NOTIFICATION);
		} else if (servicio.getValue()==null) {
			Notification.show("Elige servicio " , Type.ASSISTIVE_NOTIFICATION);
		} else if ( idioma.getValue()==null) {
			Notification.show("Elige idioma " , Type.ASSISTIVE_NOTIFICATION);
		} else {
		col3.removeAllComponents();
		col3.addComponent(verPdf(cgine.getValue(), "ginecologia", idioma.getValue()));
		}
	}

	public void clickVerPdfO() {
		if (cgine.getValue()==null) {
			Notification.show("Elige consentimiento " , Type.ERROR_MESSAGE);
		} else if (servicio.getValue()==null) {
			Notification.show("Elige servicio " , Type.ERROR_MESSAGE);
		} else if (idioma.getValue()==null) {
			Notification.show("Elige idioma " , Type.ERROR_MESSAGE);
		} else {
		col3.removeAllComponents();
		col3.addComponent(verPdf(cosbte.getValue(), "obstetricia", idioma.getValue()));
		}
	}

	public void clickServicio() {
		if (servicio.getValue().equals("Ginecología")) {
			cgine.setVisible(true);
			cosbte.setVisible(false);
		} else {
			cgine.setVisible(false);
			cosbte.setVisible(true);
		}
	}

	public Embedded verPdf(String consentimiento, String servicio, String idioma) {
		String fichero = servicio + System.getProperty("file.separator") + idioma + System.getProperty("file.separator") + getNombreConsentimiento(servicio, consentimiento) ;;
		File pdfFile = new File("/Users/JuanNieto/Downloads/TELEOFT.pdf");
		Embedded pdf = new Embedded(idioma + " : "+ consentimiento, new FileResource(pdfFile));
		pdf.setMimeType("application/pdf");
		pdf.setType(Embedded.TYPE_BROWSER);
		pdf.setHeight("600px");
		pdf.setWidth("660px");
		return pdf;
	}

	public String getNombreConsentimiento(String servicio, String consentimiento) {
		int i = 1;
		if (servicio.equals("ginecologia")) {
			for (String co : cgines) {
				if (co.equals(consentimiento))
					return Integer.toString(i);
				else
					i++;
			}
		} else {
			for (String co : cosbtes) {
				if (co.equals(consentimiento))
					return Integer.toString(i);
				else
					i++;
			}
		}
		return "";
	}

	public PantallaSeleccion(Component... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

}
