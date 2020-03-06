package Constantes;

import java.io.InputStream;
import java.net.URL;

public class ConsPaths {
	public static final String PATH_FUNDO = "";
	public static final URL PATH_NULL = ConsPaths.class.getResource("/Vis\u00E3o/imagens/imagemNull.jpg");
	public static final URL PATH_DOWN = ConsPaths.class.getResource("/Vis\u00E3o/imagens/down.png");
	public static final InputStream TESTE = ConsPaths.class.getResourceAsStream("/Vis\u00E3o/imagens/imagemNull.jpg");
}
