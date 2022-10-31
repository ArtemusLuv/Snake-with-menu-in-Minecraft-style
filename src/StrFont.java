import java.awt.*;
import java.io.File;
import java.io.IOException;

/*
 *  Class for realization your custom font
 */

public class StrFont {

    Font mine_font;

    public StrFont() {
        try {
            mine_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Font/MinecraftRegular.otf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Font/MinecraftRegular.otf")));
        } catch (IOException | FontFormatException e) {}
    }

}
