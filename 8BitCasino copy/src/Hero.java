//Blake Ankner
//

import java.awt.*;

public class Hero {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public int dx;
    public int dy;

    public int money;
    public String name;
    public String password;

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public Rectangle rec;

    public Hero(int pXpos, int pYpos, int dxParameter, int dyParameter){
        xpos = pXpos;
        ypos = pYpos;
        dx = dxParameter;
        dy = dyParameter;
        width = 50;
        height = 50;
        name = "Blank";
        money = 500;

        up = false;
        down = false;
        left = false;
        right = false;

        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
//        System.out.println("xpos: " + xpos + "ypos: "+ ypos + "ypos&height: " + (ypos + height));
        if (left){
            xpos = xpos - dx;
            if (xpos<0){
                xpos = 0;
            }
        }
        if (right){
            xpos = xpos+ dx;
            if (xpos>1000-width){
                xpos = 1000-width;
            }
        }

        if (up){
            ypos = ypos - dy;
            if (ypos<0){
                ypos = 0;
            }
        }

        if (down){
            ypos = ypos + dy;
            if (ypos>600+height){
                ypos = 700-height;
            }
        }
        rec = new Rectangle(xpos, ypos, width, height);

    }
}
