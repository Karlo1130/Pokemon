import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ControladorTile {
	
	AdminitradorJuego aj;
	public Tile[] tile;
	public int numTileMap[][];

	
	public ControladorTile(AdminitradorJuego aj) {
		
		this.aj = aj;
		
		tile = new Tile[10];
		numTileMap = new int[aj.mundoCol][aj.mundoFil];
		
		cargarTile();
		cargarMapa("/mapas/mapa1.txt");
	}
	
	public void cargarTile() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Tierra.png"));
			tile[0].colision = true;
			
			tile[1] = new Tile();
			tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Hierva.png"));
			
			tile[2] = new Tile();
			tile[2].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Agua.png"));
			
			tile[3] = new Tile();
			tile[3].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Tierra.png"));
			
			tile[4] = new Tile();
			tile[4].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Arbusto.png"));
			
			tile[5] = new Tile();
			tile[5].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Lodo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void cargarMapa(String rutaArchivo) {
		try {
			//EXPORTA EL MAPA
			InputStream mapa1 = getClass().getResourceAsStream(rutaArchivo);
			//LEE EL MAPA
			BufferedReader br = new BufferedReader(new InputStreamReader(mapa1));
			
			int col = 0;
			int fil = 0;
			
			while(col < aj.mundoCol && fil < aj.mundoFil) {
				
				String linea = br.readLine();
				
				while(col < aj.mundoCol) {
					
					String numeros[] = linea.split(" ");
					
					int num = Integer.parseInt(numeros[col]);
					
					numTileMap[col][fil] = num;
					col++;
				}
				if(col == aj.mundoCol) {
					col = 0;
					fil++;
				}
			}
			br.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public void dibujar(Graphics2D g2) {
		
		int MundoCol = 0;
		int MundoFil = 0;
		
		while(MundoCol < aj.mundoCol && MundoFil < aj.mundoFil) {
			
			int numTile = numTileMap[MundoCol][MundoFil];
			
			int mundoX = MundoCol * aj.tamPantalla;
			int mundoY = MundoFil * aj.tamPantalla;
			int pantallaX = mundoX - aj.jugador.MundoX + aj.jugador.pantallaX;
			int pantallaY = mundoY - aj.jugador.MundoY + aj.jugador.pantallaY;
			
			g2.drawImage(tile[numTile].imagen, pantallaX, pantallaY, aj.tamPantalla, aj.tamPantalla, null);
			/*g2.setColor(Color.blue);
			g2.fillRect(x, y, 25, 25);*/
			
			MundoCol++;
			
			if(MundoCol == aj.mundoCol) {
				MundoCol = 0;
				MundoFil++;
			}
		}
		
	}
}
