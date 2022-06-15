package logic.Dynamic;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	// Tamanio de la memoria
	private int size = 128;
	// Almacen de los sectores de memoria de igual tamanio
	private List<Sector> sectors = new ArrayList<Sector>();
	// Almacena el indice del ultimo sector de memoria que se utilizo
	private int lastInsertedIndex = 0;

	public Memory(int size) {
		this.size = size;
	}

	// Elije el bloque del tamanio mas aproximado
	public int bestFit(int size) {

		// Si la memoria esta vacia
		if (this.sectors.size() == 0) {

			Sector sectorBusy = new Sector(size);
			Sector sectorFree = new Sector(this.size - size);

			sectorBusy.setFreeSpace(0);

			sectorFree.setFree(true);
			sectorFree.setFreeSpace(this.size - size);

			this.sectors.add(sectorBusy);
			this.sectors.add(sectorFree);

			return 0;
		}

		// Si la memoria no esta vacia
		if (this.sectors.size() > 0) {

			// Guarda el sector con tamanio mas pequenio
			int minSize = 128;
			// Guarda el indice en el arraylist del sector mas pequenio 
			int index = 0;

			for(int i = 0; i < this.sectors.size(); i++) {

				// Si el sector esta libre
				// Si el tamanio del sector es menor que el valor de minSize
				// Si el tamanio del sector es mayor que el tamanio del nuevo sector a ingresar
				if(
						this.sectors.get(i).getSize() <= minSize && 
						this.sectors.get(i).isFree() &&
						this.sectors.get(i).getSize() >= size) {

					minSize = this.sectors.get(i).getSize();
					index = i;
				}
			}

			// Si el sector a ingresar no ocupara todo el espacio vacio en memoria
			if(this.sectors.get(index).getSize() - size > 0) {

				// Creacion de dos sectores

				// Sector donde se almacenara el nuvo proceso
				Sector sectorBussy = new Sector(size);

				// Sector de memoria con el espacio restante (Espacio libre para ingresar nuevos procesos)
				Sector sectorFree = new Sector(this.sectors.get(index).getSize() - size);

				// Se establece a vacio el sector de memoria
				sectorFree.setFree(true);
				sectorFree.setFreeSpace(sectorFree.getSize());

				// Se reemplaza el sector vacio por el sector donde se ingreso el nuevo proceso
				this.sectors.set(index, sectorBussy);
				// Se inserta el sector de memoria vacio, inmediatamente despues del sector que se acaba de ingresar
				this.sectors.add(index + 1, sectorFree);

				return index;
			}

			// Si el sector a ingresar ocupara todo el espacio libre en memoria
			if(this.sectors.get(index).getSize() - size == 0) {

				// Se crea el sector con el espacio definido
				Sector sectorBussy = new Sector(size);

				// Se reempleaza el sector vacio por el sector nuevo
				this.sectors.set(index, sectorBussy);

				return index;
			}

		}

		// Statement final
		return -1;
	}

	// Elije el primer bloque disponible que tenga el espacio suficiente para almacenar
	// el proceso
	public int firstFit(int size) {

		// Si la memoria esta vacia
		if (this.sectors.size() == 0) {

			Sector sectorBusy = new Sector(size);
			Sector sectorFree = new Sector(this.size - size);

			sectorBusy.setFreeSpace(0);

			sectorFree.setFree(true);
			sectorFree.setFreeSpace(this.size - size);

			this.sectors.add(sectorBusy);
			this.sectors.add(sectorFree);

			return 0;
		}

		// Si la memoria no esta vacia
		if (this.sectors.size() > 0) {

			int index = 0;

			for(int i = 0; i < this.sectors.size(); i++) {

				// System.out.println(this.sectors.get(i).getSize() > size && this.sectors.get(i).isFree() );

				if(this.sectors.get(i).getSize() >= size && this.sectors.get(i).isFree()) {
					index = i;
					break;
				}
			}


			// Si el nuevo sector de memoria ocupa todo el espacio disponible en memoria
			if(this.sectors.get(index).getSize() - size == 0) {

				Sector sectorBussy = new Sector(size);

				this.sectors.set(index, sectorBussy);

				return index;
			}

			if(this.sectors.get(index).getSize() - size > 0) {

				// Creacion de dos sectores

				// Sector donde se almacenara el nuvo proceso
				Sector sectorBussy = new Sector(size);

				// Sector de memoria con el espacio restante (Espacio libre para ingresar nuevos procesos)
				Sector sectorFree = new Sector(this.sectors.get(index).getSize() - size);

				// Se establece a vacio el sector de memoria
				sectorFree.setFree(true);
				sectorFree.setFreeSpace(sectorFree.getSize());

				// Se reemplaza el sector vacio por el sector donde se ingreso el nuevo proceso
				this.sectors.set(index, sectorBussy);
				// Se inserta el sector de memoria vacio, inmediatamente despues del sector que se acaba de ingresar
				this.sectors.add(index + 1, sectorFree);

				return index;
			}
		}

		// Statement Final
		return -1;
	}

	// Elije el siguiente sector disponible despues del ultimo sector ingresado
	public int nextFit(int size) {

		// Si la memoria esta vacia
		if (this.sectors.size() == 0) {

			Sector sectorBusy = new Sector(size);
			Sector sectorFree = new Sector(this.size - size);

			sectorBusy.setFreeSpace(0);

			sectorFree.setFree(true);
			sectorFree.setFreeSpace(this.size - size);

			this.sectors.add(sectorBusy);
			this.sectors.add(sectorFree);

			return 0;
		}

		// Si la memoria no esta vacia
		if (this.sectors.size() > 0) {

			int index = 0;
			int i = lastInsertedIndex + 1;
			
			if (i >= this.sectors.size()) {
				i = 0;
			}
			
			while(i != lastInsertedIndex) {
				
				if(this.sectors.get(i).getSize() >= size && this.sectors.get(i).isFree()) {
					index = i;
					break;
				}
				
				if (i == this.sectors.size()) {
					i = 0;
					continue;
				}
				
				i++;
			}
			
			// Si el nuevo sector de memoria ocupa todo el espacio disponible en memoria
			if(this.sectors.get(index).getSize() - size == 0) {

				Sector sectorBussy = new Sector(size);

				this.sectors.set(index, sectorBussy);

				return index;
			}

			if(this.sectors.get(index).getSize() - size > 0) {

				// Creacion de dos sectores

				// Sector donde se almacenara el nuvo proceso
				Sector sectorBussy = new Sector(size);

				// Sector de memoria con el espacio restante (Espacio libre para ingresar nuevos procesos)
				Sector sectorFree = new Sector(this.sectors.get(index).getSize() - size);

				// Se establece a vacio el sector de memoria
				sectorFree.setFree(true);
				sectorFree.setFreeSpace(sectorFree.getSize());

				// Se reemplaza el sector vacio por el sector donde se ingreso el nuevo proceso
				this.sectors.set(index, sectorBussy);
				// Se inserta el sector de memoria vacio, inmediatamente despues del sector que se acaba de ingresar
				this.sectors.add(index + 1, sectorFree);

				return index;
			}
		}

		// Statement Final
		return -1;
	}

	// Libera un proceso de memoria
	public boolean killProcess(int index) {

		if (this.sectors.size() < index) {
			return false;
		}

		this.sectors.get(index).setFree(true);
		this.sectors.get(index).setFreeSpace(this.sectors.get(index).getSize());

		return true;
	}

	// Imprime los sectores de memoria
	public void getMemory() {

		if (this.sectors.size() > 0) {

			System.out.println("------------------------------------------");

			for (Sector sector : this.sectors) {
				System.out.println(sector.toString());
			}

			System.out.println("------------------------------------------");
		}
	}


	public int getSize() {return this.size;
	}
}
