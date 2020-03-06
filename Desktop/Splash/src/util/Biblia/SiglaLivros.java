package util.Biblia;

public enum SiglaLivros {
	
	Genesis(0, "gn", 50, "G�nesis"),
	Exodo(1, "ex", 40, "�xodo"),
	Levitico(2, "lv", 27, "Lev�tico"),
	Numeros(3, "nm", 36, "N�meros"),
	Deuteronomio(4, "dt", 34, "Deuteron�mio"),
	Josue(5, "js", 24, "Josu�"),
	Juizes(6, "jz", 21, "Ju�zes"),
	Rute(7, "rt", 4, "Rute"),
	Samuel_1(8, "1sm", 31, "1 Samuel"),
	Samuel_2(9, "2sm", 24, "2 Samuel"),
	Reis_1(10, "1rs", 22, "1 Reis"),
	Reis_2(11, "2rs", 25, "2 Reis"),
	Cronicas_1(12, "1cr", 29, "1 Cr�nicas"),
	Cronicas_2(13, "2cr", 36, "2 Cr�nicas"),
	Esdras(14, "ed", 10, "Esdras"),
	Neemias(15, "ne", 13, "Neemias"),
	Ester(16, "et", 10, "Ester"),
	Jo(17, "j�", 42, "J�"),
	Salmos(18, "sl", 150, "Salmos"),
	Proverbios(19, "pv", 31, "Prov�rbios"),
	Eclesiastes(20, "ec", 12, "Eclesiastes"),
	Cantares(21, "ct", 8, "C�nticos"),
	Isaias(22, "is", 66, "Isa�as"),
	Jeremias(23, "jr", 52, "Jeremias"),
	Lamentacoes(24, "lm", 5, "Lamenta��es"),
	Ezequiel(25, "ez", 48, "Ezequiel"),
	Daniel(26, "dn", 12, "Daniel"),
	Oseias(27, "os", 14, "Oseias"),
	Joel(28, "jl", 3, "Joel"),
	Amos(29, "am", 9, "Am�s"),
	Obadias(30, "ob", 1, "Obadias"),
	Jonas(31, "jn", 4, "Jonas"),
	Miqueias(32, "mq", 7, "Miqueias"),
	Naum(33, "na", 3, "Naum"),
	Habacuque(34, "hc", 3, "Habacuque"),
	Sofonias(35, "sf", 3, "Sofonias"),
	Ageu(36, "ag", 2, "Ageu"),
	Zacarias(37, "zc", 14, "Zacarias"),
	Malaquias(38, "ml", 4, "Malaquias"),
	
	Mateus(39, "mt", 28, "Mateus"),
	Marcos(40, "mc", 16, "Marcos"),
	Lucas(41, "lc", 24, "Lucas"),
	Joao(42, "jo", 21, "Jo�o"),
	Atos(43, "at", 28, "Atos"),
	Romanos(44, "rm",16, "Romanos"),
	Corintios_1(45, "1co", 16, "1 Cor�ntios"),
	Corintios_2(46, "2co", 13, "2 Cor�ntios"),
	Galatas(47, "gl", 6, "G�latas"),
	Efesios(48, "ef", 6, "Ef�sios"),
	Filipenses(49, "fp", 4, "Filipenses"),
	Colosenses(50, "cl", 4, "Colosenses"),
	Tessalonicenses_1(51, "1ts", 5, "1 Tessalonicenses"),
	Tessalonicenses_2(52, "2ts", 3, "2 Tessalonicenses"),
	Timoteo_1(53, "1tm", 6, "1 Tim�teo"),
	Timoteo_2(54, "2tm", 4, "2 Tim�teo"),
	Tito(55, "tt", 3, "Tito"),
	Filemon(56, "fm", 1, "Filemon"),
	Hebreus(57, "hb", 13, "Hebreus"),
	Tiago(58, "tg", 5, "Tiago"),
	Pedro_1(59, "1pe", 5, "1 Pedro"),
	Pedro_2(60, "2pe", 3, "2 Pedro"),
	Joao_1(61, "1jo", 5, "1 Jo�o"),
	Joao_2(62, "2jo", 1, "2 Jo�o"),
	Joao_3(63, "3jo", 1, "3 Jo�o"),
	Judas(64, "jd", 1, "Judas"),
	Apocalipse(65, "ap", 22, "Apocalipse");	
	
	
	public int valorSigla;
	public String nomeSigla;
	public int qntCapitulos;
	public String nomeLivro;
	
	SiglaLivros(int valorSigla, String nomeSigla, int qntCapitulos, String NomeLivro){
		this.valorSigla = valorSigla;
		this.nomeSigla = nomeSigla;
		this.qntCapitulos = qntCapitulos;
		this.nomeLivro = NomeLivro;
	}
}

