package main.tile;

import Physics.*;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TileManager {

    GamePanel gp;

    Tile[] tile;

    int mapTileNum[][];
    ArrayList<Tile> tiles;

    BufferedImage image = null;
    private int x = 0;
    private int i = 1;

    private int tileIndex = 0;
    private double randomnessDivisor = 1.5;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        try {
            image = ImageIO.read(new File(gp.sourceDir + "textures/green_platform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tiles = new ArrayList<>();
        x = 0;

        createNewTileRandom();

        World.getInstance().setTileManager(this);

    }

    public void createNewTileRandom() {
        if (gp.mode == "white") {
            for(int i = 0; i < 3; ++i)
            {
                try {
                    image = ImageIO.read(new File(gp.sourceDir + "textures/white_platform.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                createNewTile(image, "white");
            }


        } else {
            for(int i = 0; i < 40; ++i)
            {

                double randomNum1 = Math.random()*4; //if this is between 2 & 3 it will cause two tiles to be generated at the next y level instead of 1
                double randomNum2 = Math.random(); //if this is less than 0.6 one of the two tiles will be red and broken, else will be white
                double randomNum3 = Math.random()*4; //determines next tile type

                if (randomNum3 >= 0 && randomNum3 < 2){
                    try {
                        image = ImageIO.read(new File(gp.sourceDir + "textures/green_platform.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    createNewTile(image, "green");
                }

                else if (randomNum3 >= 2 && randomNum3 < 3){
                    try {
                        image = ImageIO.read(new File(gp.sourceDir + "textures/white_platform.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    createNewTile(image, "white");
                }

                else if (randomNum3 >= 3 && randomNum3 < 4){
                    try {
                        image = ImageIO.read(new File(gp.sourceDir + "textures/blue_platform.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    double randomNum4 = Math.random()*(gp.screenWidth-50);
                    double randomNum5 = Math.random()*(gp.screenWidth-randomNum4) + randomNum4;
                    //TODO: Get below to work
                    createNewTile(image, "blue", (double) 1, (int) randomNum4, (int) randomNum5);
                } else {
                    try {
                        image = ImageIO.read(new File(gp.sourceDir + "textures/green_platform.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    createNewTile(image, "green");

                    System.out.println("exception: TileManager's randomNum3 not found between 0 & 4");
                }

                //TODO: make it so that the below adds tile on the same y value instead of different y value
                if (randomNum1 > 2 && randomNum1 < 3) {
                    if (randomNum2 < 0.6) {
                        try {
                            image = ImageIO.read(new File(gp.sourceDir + "textures/red_platform.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        createNewTile(image, "red");
                    } else {
                        try {
                            image = ImageIO.read(new File(gp.sourceDir + "textures/white_platform.png"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        createNewTile(image, "white");
                    }
                }

            }
        }
    }

    public void createNewTile(BufferedImage image, String tag)
    {

        tileIndex ++;
        if (tileIndex > 4) {
            tileIndex = 1;
            randomnessDivisor = 5 * Math.random();
        }
        //System.out.println(randomnessDivisor);


        x = (int) ((x + gp.screenWidth / randomnessDivisor + Math.random() * gp.screenWidth / randomnessDivisor) % gp.screenWidth);
        Tile tile = new Tile(new Body(BodyType.STATIC, new Rectangle(x, 100 * i, gp.tileSize, gp.tileHeight/32 * 5)), gp, image, tag);
        i++;
        tiles.add(tile);
        World.getInstance().addActor(tile);
    }

    public void createNewTile(BufferedImage image, String tag, double dx, int Lbound, int Rbound)
    {

        tileIndex ++;
        if (tileIndex > 4) {
            tileIndex = 1;
            randomnessDivisor = 5 * Math.random();
        }
        //System.out.println(randomnessDivisor);

        x = (int) ((x + gp.screenWidth / randomnessDivisor + Math.random() * gp.screenWidth / randomnessDivisor) % gp.screenWidth);
        if (x + Rbound > gp.screenWidth) {
            Rbound = gp.screenWidth;
        }
        if (x - Lbound < 0) {
            Lbound = 0;
        }

        if (Math.random() > 0.5) {
            dx = 1;
        } else {
            dx = -1;
        }

        Tile tile = new Tile(new Body(BodyType.STATIC, new Rectangle(x, 100 * i, gp.tileSize, gp.tileHeight/32 * 5), new Vector2D(1, 0), Lbound, Rbound), gp, image, tag, dx);
        i++;
        tiles.add(tile);
        World.getInstance().addActor(tile);
    }

    public void deleteTile(Tile tile)
    {
        tiles.remove(tile);
    }

    public void loadMap() {

        try {
            String sourceDir = "";
            File is = new File(sourceDir + "maps/map1.txt");
            BufferedReader br = new BufferedReader(new FileReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

}
