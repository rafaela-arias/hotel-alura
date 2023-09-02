package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuarios;
import views.Login;
import views.MenuUsuario;

public class UsuarioControlador implements ActionListener{
	private Login login;
	
	public UsuarioControlador(Login login) {
		this.login = login;
	}
	
	public void actionPerformed(ActionEvent e) {
		String nombre = login.getNombre();
		String contraseña = login.getContraseña();
		
		if (Usuarios.validarUsuario(nombre, contraseña)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			login.dispose();
		} else {
			JOptionPane.showMessageDialog(login, "Usuario o contraseña erróneo");
		}
	}
}
