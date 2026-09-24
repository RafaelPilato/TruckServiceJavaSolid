package dominio;
import java.time.LocalDate;

public class Caminhao {
    private int id;
    private String placa;
    private String chassi;
    private String marca;
    private String modelo;
    private int ano;
    private Motorista motorista;

    public Caminhao(String placa, String chassi, String marca, String modelo, int ano, Motorista motorista) {
        if(placa == null || placa.isBlank()){
            throw new IllegalArgumentException("A placa é obrigatória!");
        }
        if(chassi == null || chassi.isBlank()){
            throw new IllegalArgumentException("O chassi é obrigatória!");
        }
        if(ano < 1990 || ano > LocalDate.now().getYear() + 1){
            throw new IllegalArgumentException("O ano é inválido!");
        }

        this.placa = placa;
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.motorista = motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public Motorista getMotorista() {
        return motorista;
    }
}
