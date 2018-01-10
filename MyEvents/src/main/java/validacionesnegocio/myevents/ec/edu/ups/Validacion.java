package validacionesnegocio.myevents.ec.edu.ups;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.myevents.ec.edu.ups.PersonaController;

// TODO: Auto-generated Javadoc
/**
 * The Class Validacion.
 */
public class Validacion {
	
	/** The Constant PATTERN_EMAIL. */
	/*
	 * Variable para la validacion de la cedula
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	
	/**
	 * Validar cedula. Metodo util en la validacion de la cedula
	 *
	 * @param cedula the cedula
	 * @return true, if successful
	 */
	
	public boolean validarCedula(String cedula) {
		System.out.println("CEDULA:  "+cedula);
		String ced = cedula;
		int sum_t = 0;
		int res = 0;
		if(cedula.length()==10) {
			for (int i = 0; i < 9; i++) {
				char b = ced.charAt(i);
				int a = b - 48;
				if (i == 0) {
					a = a * 2;
				} else {
					if (i % 2 == 0) {
						a = a * 2;
					} else {
						a = a * 1;
					}
				}
				if (a > 9) {
					a = a - 9;
				}
				sum_t = sum_t + a;
			}
			res = sum_t % 10;
			if (res != 0) {
				res = 10 - res;
			}
			boolean resultado = false;
			if (res == Integer.parseInt(ced.substring(9, 10))) {
				resultado = true;
			} else {
				resultado = false;
			}
			return resultado;
		}else {
			return false;
		}
	}
	
	/**
	 * Validar correo. Metodo para la validacion de un correo electronico
	 *
	 * @param correo the correo
	 * @return true, if successful
	 */

	public boolean validarCorreo(String correo) {
		System.out.println("CORREO:  "+correo);
		String email = correo;
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}

}
