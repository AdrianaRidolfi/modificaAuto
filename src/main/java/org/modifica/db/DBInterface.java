package org.modifica.db;

import org.modifica.Auto;

public interface DBInterface {
	
	void modificaAuto(int id, String campo, String parametro);
	
	Auto getAuto(int id);

}
