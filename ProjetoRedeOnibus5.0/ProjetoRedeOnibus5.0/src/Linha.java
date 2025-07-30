
public class Linha {

    //Variaveis da Classe Linha
    private int id;
    private String nomeRota;
    private int quantidadeParadas;
    private String inicio;
    private String fim;

    //Construtor da Classe Linha
    public Linha(int id, String nomeRota, int quantidadeParadas, String inicio, String fim) {
        this.id = id;
        this.nomeRota = nomeRota;
        this.quantidadeParadas = quantidadeParadas;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getId() {
        return id;
    }

    public String getNomeRota() {
        return nomeRota;
    }

    public int getQuantidadeParadas() {
        return quantidadeParadas;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public void setQuantidadeParadas(int quantidadeParadas) {
        this.quantidadeParadas = quantidadeParadas;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }
    
    //Override evita erros
    //String toString serve para facilitar a visualização das informações
    @Override
    public String toString() {
        return "ID: " + id
                + ", Nome da Rota: " + nomeRota
                + ", Quantidade de Paradas: " + quantidadeParadas
                + ", Início: " + inicio
                + ", Final: " + fim;
    }
}
