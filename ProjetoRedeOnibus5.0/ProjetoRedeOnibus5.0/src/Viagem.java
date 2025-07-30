
public class Viagem {

    //Variaveis da Classe Viagem
    private String horarioSaida;
    private String dataSaida;
    private String dataChegada;
    private String valorPassagem;
    private int quantidadePassageiros;
    private Onibus onibus;
    private Linha linha;

    //Construtor da Classe Viagem
    public Viagem(String horarioSaida, String dataSaida, String dataChegada, String valorPassagem) {
        this.horarioSaida = horarioSaida;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.valorPassagem = valorPassagem;
        this.quantidadePassageiros = 0;
    }

    public String getHorario() {
        return horarioSaida;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public String getValorPassagem() {
        return valorPassagem;
    }

    public int getQuantidadePassageiros() {
        return quantidadePassageiros;
    }

    public void setHorario(String horario) {
        this.horarioSaida = horario;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setValorPassagem(String valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public void setQuantidadePassageiros(int quantidadePassageiros) {
        this.quantidadePassageiros = quantidadePassageiros;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    //Override evita erros
    //String toString serve para facilitar a visualização das informações
    @Override
    public String toString() {
        return "Horário: " + horarioSaida
                + ", Data de Saída: " + dataSaida
                + ", Data de Chegada: " + dataChegada
                + ", Valor da Passagem: R$ " + valorPassagem
                + ", Quantidade de Passageiros: " + quantidadePassageiros;
    }
}
