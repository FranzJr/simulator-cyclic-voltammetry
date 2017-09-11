package co.edu.unal.voltammetry.fitting;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @source: (C) Angel Franco García. Universidad del País Vasco (España)
 *
 */
public class RegresionApplet2 extends Applet {
     MiCanvas canvas;

     Panel bevelPanel1 = new Panel();
     Panel bevelPanel2 = new Panel();
     Panel bevelPanel3 = new Panel();
     Button btnCalcular = new Button();
     Button btnBorrar = new Button();
     FlowLayout flowLayout1 = new FlowLayout();
     BorderLayout borderLayout1 = new BorderLayout();
     BorderLayout borderLayout3 = new BorderLayout();
     BorderLayout borderLayout4 = new BorderLayout();
    Panel Panel4 = new Panel();
   TextField[] textDatoX=new TextField[10];
    TextField[] textDatoY=new TextField[10];

    Label labelX=new Label();
    Label labelY=new Label();
    Button btnGrado=new Button();
    TextField textGrado=new TextField();
     GridBagLayout gbl1 = new GridBagLayout();
     GridBagConstraints gbc1=new GridBagConstraints();
    BorderLayout borderLayout2 = new BorderLayout();
    int nGrado=1;
    double[] x, y;
    final double[] xDatos={0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4};
    final double[] yDatos={0.375, 0.625, 0.750, 0.812, 0.875, 1.0, 1.062, 1.125};

  //Initialize the applet
  public void init() {
     try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
  }

  //Component initialization
  public void jbInit() throws Exception{
     int ancho = Integer.parseInt(this.getParameter("WIDTH"));
     int alto = Integer.parseInt(this.getParameter("HEIGHT"));
     this.setSize(new Dimension(ancho, alto));
     canvas=new MiCanvas();
     bevelPanel1.setBackground(Color.lightGray);
     bevelPanel3.setBackground(Color.gray);
        Panel4.setBackground(Color.lightGray);

   ValidaCaracter valChar=new ValidaCaracter();
      for(int i=0; i<textDatoX.length; i++){
        textDatoX[i]=new TextField();
        textDatoY[i]=new TextField();
        textDatoX[i].setColumns(7);
        textDatoY[i].setColumns(7);
        textDatoX[i].addKeyListener(valChar);
        textDatoY[i].addKeyListener(valChar);
     }
     labelX.setText("   X   ");
     labelY.setText("   Y   ");
     btnCalcular.setLabel("Calcular");
     btnGrado.setLabel("Grado polinomio >>");

       Accion accion=new Accion(this);
        btnCalcular.addActionListener(accion);
        btnBorrar.addActionListener(accion);
        btnGrado.addActionListener(accion);

    textGrado.setColumns(2);
     textGrado.setText("1");
     textGrado.setEditable(false);
     //panel 4
     Panel4.setLayout(gbl1);
     gbc1.anchor=GridBagConstraints.NORTH;
     gbc1.gridwidth=1;
     Panel4.add(labelX, gbc1);
     gbc1.gridwidth=GridBagConstraints.REMAINDER;
     Panel4.add(labelY, gbc1);
     gbc1.insets=new Insets(1,2,1,2);
     for(int i=0; i<textDatoX.length; i++){
        gbc1.anchor=GridBagConstraints.WEST;
        gbc1.gridwidth=1;
        Panel4.add(textDatoX[i], gbc1);
        gbc1.gridwidth=GridBagConstraints.REMAINDER;
        Panel4.add(textDatoY[i], gbc1);
     }
     gbc1.insets=new Insets(5,0,0,0);
     gbc1.anchor=GridBagConstraints.CENTER;
     gbc1.gridwidth=1;
     Panel4.add(btnGrado, gbc1);
     gbc1.gridwidth=GridBagConstraints.REMAINDER;
     Panel4.add(textGrado, gbc1);


     //panel 5
     bevelPanel1.setLayout(borderLayout2);
     bevelPanel2.setLayout(borderLayout1);
     this.setLayout(borderLayout4);
     this.add(bevelPanel1, BorderLayout.WEST);
     this.add(bevelPanel2, BorderLayout.CENTER);

     bevelPanel3.setLayout(flowLayout1);
     btnBorrar.setLabel("Borrar");
    bevelPanel1.add(bevelPanel3, BorderLayout.SOUTH);
    bevelPanel3.add(btnCalcular, null);
    bevelPanel3.add(btnBorrar, null);
        bevelPanel1.add(Panel4, BorderLayout.CENTER);
    bevelPanel2.add(canvas, BorderLayout.CENTER);

//controles de edición, datos iniciales
    textDatoX[0].requestFocus();
    for(int i=0; i<xDatos.length; i++){
        textDatoX[i].setText(String.valueOf(xDatos[i]));
        textDatoY[i].setText(String.valueOf(yDatos[i]));
    }

}
 int obtenerDatos(){
      String datoX, datoY;
      double[] numX=new double[10];
      double[] numY=new double[10];
      int i=0;
      for(i=0; i<textDatoX.length; i++){
        datoX=textDatoX[i].getText();
        datoY=textDatoY[i].getText();
        if((datoX.length()==0)|| (datoX.length()==0))  break;
        numX[i]=Double.valueOf(datoX).doubleValue();
        numY[i]=Double.valueOf(datoY).doubleValue();
      }
      if(i>2){
        x=new double[i];
        y=new double[i];
        for(int j=0; j<i; j++){
            x[j]=numX[j];
            y[j]=numY[j];
        }
      }
      return i;
 }


  void btnCalcular_actionPerformed(ActionEvent e) {
    int nDatos=obtenerDatos();
    int grado=Integer.parseInt(textGrado.getText());
    if(nDatos<3 || grado>nDatos){
            return;
    }
    canvas.setDatos(x, y, grado);
  }
  void btnBorrar_actionPerformed(ActionEvent e) {
    for(int i=0; i<textDatoX.length; i++){
        textDatoX[i].setText("");
        textDatoY[i].setText("");
      }
    textDatoX[0].requestFocus();
  }
  void btnGrado_actionPerformed(ActionEvent e) {
    nGrado++;
    int nDatos=obtenerDatos();
    if(nGrado>nDatos)
        nGrado=1;
    textGrado.setText(String.valueOf(nGrado));
  }

//Get Applet information
  public String getAppletInfo() {
    return "(C) Angel Franco García. Universidad del País Vasco (España)";
  }

}
class ValidaCaracter implements KeyListener{
     public void keyPressed(KeyEvent ev){
        char letra=ev.getKeyChar();
        if(letra!='.'&& letra!='-'&& letra!='\b'&& letra!='\t'){
          if(letra<'0' || letra>'9'){
            ev.consume();
          }
        }
     }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}


class Accion implements ActionListener{
    RegresionApplet2 applet;
    public Accion(RegresionApplet2 applet){
      this.applet=applet;
    }
    public void actionPerformed(ActionEvent e) {
      String titulo=e.getActionCommand();
      if(titulo.equals("Calcular"))
        applet.btnCalcular_actionPerformed(e);
      else if (titulo.equals("Borrar"))
        applet.btnBorrar_actionPerformed(e);
      else
        applet.btnGrado_actionPerformed(e);
    }
}
