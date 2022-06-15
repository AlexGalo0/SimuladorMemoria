package logic.Fijo;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	// Tamanio de la memoria
	private int size=128;
	// Almacen de los sectores de memoria de igual tamanio
	private List<Sector> equalSectors = new ArrayList<Sector>();
	// Almacen de los sectores de memoria de distinto tamanio
	private List<Sector> differentSectors = new ArrayList<Sector>();
	
	public Memory(int size) {
		this.size = size;
	}
	
	// Se llena el arreglo con los sectores de memoria vacios y del mismo tamanio
	public void setEqualSectors() {
		
		// Se utiliza un numero random 
		int pow = (int) ((Math.random() * (6 - 3)) + 2);
		
		// Se utiliza potencias de 2 para poder obtener el tamanio de los sectores y el numero de sectores
		int number = (int) Math.pow(2, pow);
		int size = (int) this.size/number;
		
		// Con un ciclo for se crean los objetos del tipo Sector y se llena el ArrayList
		for(int i = 0; i < number; i++) {
			
			Sector sector = new Sector(size);
			equalSectors.add(sector);
		}	
	}
		
	// Se obtiene una impresion de los sectores almacenados en el ArrayList
	public void getEqualSectors() {
		
		for (Sector sector : equalSectors) {
			System.out.println(sector.toString());
		}	
	}

	//MODIFICADA Y AGREGADAS CMMZ

	public int getNumeroSectores(){
		return equalSectors.size();
	}

	public int sectorSizeforEquals(){
		return equalSectors.get(0).getSize();
	}

	public boolean putProcessEqualV2(int size,int index) {

		// Se comprueba si la "memoria" contiene sectores ingresados
		if(this.equalSectors.size() == 0) {
			return false;
		}

		// Se Comprueba si el tamanio del proceso a ingresar puede ser contenido por algun sector de memoria
		if(this.equalSectors.get(0).getSize() < size) {
			return false;
		}

			// Se comprueba si el sector esta libre
			if(equalSectors.get(index).isFree() == true) {

				// Se establece a false la varibale isFree del objeto sector contenido en
				// el arreglo que simula ser la memoria
				equalSectors.get(index).setFree(false);
				// Se establece el valor de la variable freeSpace del objeto sector contenido en el arreglo que
				// simula ser la memoria
				equalSectors.get(index).setFreeSpace(equalSectors.get(index).getSize()-size);


			}


		return true;
	}

	public int indexSector(){

		int indexSectorUsed=0;
		for (int i = 0; i <equalSectors.size() ; i++) {
			indexSectorUsed=(equalSectors.get(i).isFree())?0:i;

		}
		return indexSectorUsed;
	}

	public boolean availableSector(int index){
		return equalSectors.get(index).isFree();
	}

	//MODIFICADAS Y AGREGADAS PARA DIFFERENT SECTORS CMMZ

	public int getNumeroSectoresDiff(){
		return differentSectors.size();
	}

	public int sectorSizeForDiff(int index){
		return differentSectors.get(index).getSize();
	}

	public boolean availableSectorDiff(int index){
		return differentSectors.get(index).isFree();
	}

	public boolean putProcessDifferentV2(int size,int index) {

		// Comprobando si la memoria tiene sectores ingresados
		if(this.differentSectors.size() == 0) {
			return false;
		}

		if(this.differentSectors.get(index).isFree() == true && size <= this.differentSectors.get(index).getSize())
		{

				// Se establece a false la varibale isFree del objeto Sector
			this.differentSectors.get(index).setFree(false);
				// Se establece el valor de la variable freeSpace del objeto Sector
			this.differentSectors.get(index).setFreeSpace(this.differentSectors.get(index).getSize()-size);

		}

		return true;
	}


	
	// Se etablecen los sectores de memoria de diferente tamanio
	public void setDifferentSectors() {
				
		int acum = 0;
		
		while(acum <= this.size) {
			
			int pow = (int) ((Math.random() * (6 - 2)) + 2);
			int number = (int) Math.pow(2, pow);
			int size = (int) this.size/number;
			
			if(acum + size <= this.size) {
				
				Sector sector = new Sector(size);
				differentSectors.add(sector);
				
				acum = acum + size;
			}	
			
			if(acum + size > this.size) {
				
				if(acum < this.size) {
					
					size = this.size - acum;
					Sector sector = new Sector(size);
					differentSectors.add(sector);
					
					acum = acum + size;
					
				}
				
				if(acum > this.size) {
					
					size = acum - this.size;
					Sector sector = new Sector(size);
					differentSectors.add(sector);
					
					acum = acum + size;
					
				}
				
			}
			
			 //System.out.println("Acum " + acum);
			 
			 if(acum == this.size) {
					break;
			}
				
		}
	}
	
	// Se obtiene una impresion de los sectores almacenamientos en el ArrayList
	public void getDifferentSectors() {
		
		for(Sector sector : differentSectors) {
			System.out.println(sector.toString());
		}
		
	}

	// Se ingresa un proceso en un sector de memoria
	public boolean putProcessEqual(int size) {
		
		// Se comprueba si la "memoria" contiene sectores ingresados
		if(this.equalSectors.size() == 0) {
			return false;
		}
		
		// Se Comprueba si el tamanio del proceso a ingresar puede ser contenido por algun sector de memoria
		if(this.equalSectors.get(0).getSize() < size) {
			return false;
		}
		
		int acum = 0;
		
		while(acum <= this.equalSectors.get(0).getSize()  *5) {
			
			// Se obtiene el indice del sector para comprobar si se esta libre y puede 
			// contener el proceso que se quiere ingresar
			int index = (int) ((Math.random() * (this.equalSectors.size() - 0)) + 0);
			
			// Se comprueba si el sector esta libre
			if(equalSectors.get(index).isFree() == true) {
				
				// Se establece a false la varibale isFree del objeto sector contenido en 
				// el arreglo que simula ser la memoria
				equalSectors.get(index).setFree(false);
				// Se establece el valor de la variable freeSpace del objeto sector contenido en el arreglo que 
				// simula ser la memoria
				equalSectors.get(index).setFreeSpace(equalSectors.get(index).getSize()-size);
				
				break;
			}
			
			acum++;
		}
		
		return true;
	}

	// Se ingresa un proceso en un sector de memoria 
	public boolean putProcessDifferent(int size) {
		
		// Comprobando si la memoria tiene sectores ingresados
		if(this.differentSectors.size() == 0) {
			return false;
		}
		
		int acum = 0;
		
		while(acum <= this.differentSectors.size() * 5) {
			
			// Se obtiene el indice del sector para comprobar si se esta libre y puede 
			// contener el proceso que se quiere ingresar
			int index = (int) ((Math.random() * (this.differentSectors.size() - 0)) + 0);
			
			if(this.differentSectors.get(index).isFree() == true && size <= this.differentSectors.get(index).getSize()) {
				
				// Se establece a false la varibale isFree del objeto Sector
				this.differentSectors.get(index).setFree(false);
				// Se establece el valor de la variable freeSpace del objeto Sector
				this.differentSectors.get(index).setFreeSpace(this.differentSectors.get(index).getSize()-size);
				break;
			}
			
			acum++;
			
		}
		
		return true;
	}

	// Se elimina el proceso segun el indice del sector en memoria
	public boolean killProcessEqual(int index) {
		
		if(index < 0) {
			return false;
		}
		
		if(index > this.equalSectors.size()) {
			return false;
		}
		
		if(this.equalSectors.get(index).isFree() == false) {
			
			this.equalSectors.get(index).setFree(true);
			this.equalSectors.get(index).setFreeSpace(this.equalSectors.get(index).getSize());
			return true;
		}
				
		return false;
	}
	
	// Se elimina el proceso segun el indice del sector en memoria
	public boolean killProcessDifferent(int index) {
		
		if(index < 0) {
			return false;
		}
		
		if(index > this.differentSectors.size()) {
			return false;
		}
		
		if(this.differentSectors.get(index).isFree() == false) {
			
			this.differentSectors.get(index).setFree(true);
			this.differentSectors.get(index).setFreeSpace(this.differentSectors.get(index).getSize());
			return true;
		}
				
		return false;
		
	}

}
