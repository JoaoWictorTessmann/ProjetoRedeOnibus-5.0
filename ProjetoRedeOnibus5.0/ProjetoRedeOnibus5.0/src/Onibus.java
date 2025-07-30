
public class Onibus {

    //Variaveis da Classe Onibus
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int capacidade;

    //Construtor da Classe Onibus
    public Onibus(String placa, String marca, String modelo, String cor, int capacidade) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.capacidade = capacidade;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    //Override evita erros
    //String toString serve para facilitar a visualização das informações
    @Override
    public String toString() {
        return "Placa: " + placa
                + ", Marca: " + marca
                + ", Modelo: " + modelo
                + ", Cor: " + cor
                + ", Capacidade: " + capacidade;
    }
}
