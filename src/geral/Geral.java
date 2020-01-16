/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geral;

import exceptions.ValidacaoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sérgio Damião
 */
public class Geral {

    NumberFormat duasDecimais = new DecimalFormat("########0.00", new DecimalFormatSymbols(Locale.ENGLISH));


    public String CortaString(String atual, int tamanho) {
        String substring = "";
        if (atual == null) {
            substring = "";
        } else {
            if (atual.length() > tamanho) {
                substring = atual.substring(0, tamanho);
            } else {
                substring = atual;
            }
        }
        return substring;
    }
    public Date convertStringToData(String dataString) throws ValidacaoException {
        if (dataString.length() != 10) {
            throw new ValidacaoException("Data Invalida");
        }
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            formatador.setLenient(false);
            return formatador.parse(dataString.toString());
        } catch (ParseException ex) {
            throw new ValidacaoException(ex.getMessage() + "\n Data Invalida");
        }
    }

    public static String formatDate(String dateIn) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dFormat.parse(dateIn);
            dFormat.applyPattern("dd/MM/yyyy");
            return dFormat.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(Geral.class.getName()).log(Level.SEVERE, null, ex);
            return dateIn;
        }
    }


    public static String converteddMMaaaaAAAAmmDD(String dataString) {
        return dataString.substring(6, 10) + "/" + dataString.substring(3, 5) + "/" + dataString.substring(0, 2);
    }

    public static String converteAAAAmmDDddMMaaaa(String dataString) {
        return dataString.substring(8, 10) + "/" + dataString.substring(5, 7) + "/" + dataString.substring(0, 4);
    }

    public static String converteDataaaaamm(String dataString) {
        return dataString.substring(6, 10) + dataString.substring(3, 5);
    }

    public static String converteDataaaaaBmm(String dataString) {
        return dataString.substring(6, 10) + "/" + dataString.substring(3, 5);
    }

    public static Date convertStringToDataAnoMesDia(String dataString) throws ValidacaoException {
        if (dataString.length() != 10) {
            throw new ValidacaoException("Formato diferente de __/__/____\n Data Invalida");
        } else {
            try {
                SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
                formatador.setLenient(false);
                return formatador.parse(dataString);
            } catch (ParseException ex) {
                throw new ValidacaoException(ex.getMessage() + "\n Data Invalida");
            }
        }
    }

    public static Date convertStringToDataDiaMesAno(String dataString) throws ValidacaoException {
        if (dataString.length() != 10) {
            throw new ValidacaoException("Formato diferente de __/__/____\n Data Invalida");
        } else {

            try {
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                formatador.setLenient(false);
                return formatador.parse(dataString);
            } catch (ParseException ex) {
                throw new ValidacaoException(ex.getMessage() + "\n Data Invalida");
            }
        }
    }

    public static int diferencaEntreDatas(String data1, String data2) throws ParseException {
        GregorianCalendar ini = new GregorianCalendar();
        GregorianCalendar fim = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ini.setTime(sdf.parse(data1));
        fim.setTime(sdf.parse(data2));
        long dt1 = ini.getTimeInMillis();
        long dt2 = fim.getTimeInMillis();
        int dias = (int) ((dt1 - dt2) / 86400000);
        if (dias > 0) {
            return dias + 1;
        } else {
            return dias;
        }
    }

    public static String NomeDoMes(int i, int tipo) {
        // tipo diferente de 0 retorna as 3 primeiras letras do mes
        String mes[] = {"janeiro", "fevereiro", "março", "abril",
            "maio", "junho", "julho", "agosto", "setembro", "outubro",
            "novembro", "dezembro"};
        if (tipo == 0) {
            return (mes[i - 1]);
        } else {
            return (mes[i - 1].substring(0, 3));
        }
    }

    public static String DiaDaSemana(int i, int tipo) {
        String diasem[] = {"domingo", "segunda-feira", "terça-feira",
            "quarta-feira", "quinta-feira", "sexta-feira", "sábado"};
        if (tipo == 0) {
            return (diasem[i - 1]);
        } else {
            return (diasem[i - 1].substring(0, 3));
        }
    }

    public static String LocalDataPorExtenso(String cidade, String dt) {

        int d = Integer.valueOf(dt.substring(0, 2));
        int m = Integer.valueOf(dt.substring(3, 5));
        int a = Integer.valueOf(dt.substring(6, 10));

        return (cidade + ", " + d + " de " + NomeDoMes(m, 0) + " de "
                + a + ".");
    }

    public static String valorPorExtenso(double vlr) {
        if (vlr == 0) {
            return ("zero");
        }

        long inteiro = (long) Math.abs(vlr); // parte inteira do valor
        double resto = vlr - inteiro;       // parte fracionÃ¡ria do valor

        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 10) {
            return ("Erro: Valor superior ao tamanho do campo BD!");
        }

        String s = "", saux, vlrP;
        String centavos = String.valueOf((int) Math.round(resto * 100));

        String[] unidade = {"", "um", "dois", "trÃªs", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze",
            "doze", "treze", "quatorze", "quinze", "dezesseis",
            "dezessete", "dezoito", "dezenove"};
        String[] centena = {"", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"};
        String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa"};
        String[] qualificaS = {"", "mil", "milhao", "bilhao", "trilhao"};
        String[] qualificaP = {"", "mil", "milhoes", "bilhoes", "trilhoes"};

        // definindo o extenso da parte inteira do valor
        int n, unid, dez, cent, tam, i = 0;
        boolean umReal = false, tem = false;
        while (!vlrS.equals("0")) {
            tam = vlrS.length();
            // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
            // 1a. parte = 789 (centena)
            // 2a. parte = 456 (mil)
            // 3a. parte = 123 (milhÃµes)
            if (tam > 3) {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            } else { // Ãºltima parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000")) {
                saux = "";
                if (vlrP.equals("100")) {
                    saux = "cem";
                } else {
                    n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
                    cent = n / 100;                  // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10;           // unid = 1 (unidade um)
                    if (cent != 0) {
                        saux = centena[cent];
                    }
                    if ((dez != 0) || (unid != 0)) {
                        if ((n % 100) <= 19) {
                            if (saux.length() != 0) {
                                saux = saux + " e " + unidade[n % 100];
                            } else {
                                saux = unidade[n % 100];
                            }
                        } else {
                            if (saux.length() != 0) {
                                saux = saux + " e " + dezena[dez];
                            } else {
                                saux = dezena[dez];
                            }
                            if (unid != 0) {
                                if (saux.length() != 0) {
                                    saux = saux + " e " + unidade[unid];
                                } else {
                                    saux = unidade[unid];
                                }
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001")) {
                    if (i == 0) // 1a. parte do valor (um real)
                    {
                        umReal = true;
                    } else {
                        saux = saux + " " + qualificaS[i];
                    }
                } else if (i != 0) {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0) {
                    s = saux + ", " + s;
                } else {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0) {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // prÃ³ximo qualificador: 1- mil, 2- milhÃ£o, 3- bilhÃ£o, ...
        }

        if (s.length() != 0) {
            if (umReal) {
                s = s + " real";
            } else if (tem) {
                s = s + " reais";
            } else {
                s = s + " de reais";
            }
        }

        // definindo o extenso dos centavos do valor
        if (!centavos.equals("0")) { // valor com centavos
            if (s.length() != 0) // se nÃ£o Ã© valor somente com centavos
            {
                s = s + " e ";
            }
            if (centavos.equals("1")) {
                s = s + "um centavo";
            } else {
                n = Integer.parseInt(centavos, 10);
                if (n <= 19) {
                    s = s + unidade[n];
                } else {             // para n = 37, tem-se:
                    unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
                    dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
                    s = s + dezena[dez];
                    if (unid != 0) {
                        s = s + " e " + unidade[unid];
                    }
                }
                s = s + " centavos";
            }
        }
        return (s);
    }

    public static double arredondar(double valor, int casas, int ceilOrFloor) {
        /*
         1 - Valor a arredondar. 
         2 - Quantidade de casas depois da vírgula. 
         3 - Arredondar para cima ou para baixo? Para cima = 0 (ceil) Para baixo = 1 ou qualquer outro inteiro (floor)
         */
        double arredondado = valor;
        arredondado *= (Math.pow(10, casas));
        if (ceilOrFloor == 0) {
            arredondado = Math.ceil(arredondado);
        } else {
            arredondado = Math.floor(arredondado);
        }
        arredondado /= (Math.pow(10, casas));
        return arredondado;
    }

        public static String completaComZero(String palavra, int tamanho) {
        String r = String.valueOf(palavra).trim();
        int tam = r.length();
        for (int j = tam; j <= tamanho - 1; j++) {
            r = "0" + r;
        }
        return r;
    }
        
    public static void copiaArquivo(File origem, File destino) throws IOException {
        try {
            InputStream in = new FileInputStream(origem);
            OutputStream out = new FileOutputStream(destino);           // Transferindo bytes de entrada para saída
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro Copia arquivo" + e.toString());
        }
    }

    public static void moveArquivos(File origem, File destino) throws IOException {
        try {
            InputStream in = new FileInputStream(origem);
            OutputStream out = new FileOutputStream(destino);           // Transferindo bytes de entrada para saída
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            origem.delete();
        } catch (FileNotFoundException e) {
            System.out.println("Erro mover arquivo " + e.toString());

        }
    }

    public static void moveArquivosDeDirectorio(File diretorioOrigem, File diretorioDestino) {
        if (diretorioOrigem.isDirectory()) {
            if (!diretorioDestino.exists()) {
                diretorioDestino.mkdir();
            }
            String[] children = diretorioOrigem.list();
            for (String children1 : children) {
                try {
                    moveArquivos(new File(diretorioOrigem, children1), new File(diretorioDestino, children1));
                } catch (IOException ex) {
                    Logger.getLogger(Geral.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
