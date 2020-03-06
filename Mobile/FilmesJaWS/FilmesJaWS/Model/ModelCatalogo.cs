using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FilmesJaWS.Model
{
    public class ModelCatalogo
    {
        public int IDCat { get; set; }
        public string Nome { get; set; }
        public int Episodio { get; set; }
        public int Ano { get; set; }
        public string Caminho { get; set; }
        public string Sinopse { get; set; }
        public int Temporada { get; set; }
        
    }
}