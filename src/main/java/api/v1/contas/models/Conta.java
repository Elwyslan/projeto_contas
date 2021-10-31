package api.v1.contas.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="tb_contas_jpa",
       uniqueConstraints={@UniqueConstraint(name = "AsContasDevemSerUnicas", columnNames = {"agencia", "numero_conta","codigo_banco"}) }
)
public class Conta implements Serializable{

	private static final long serialVersionUID = 7399743224299447913L;
	
	@Id
	private int id;
	
	@Column(nullable=false, name="agencia")
	private int agencia;
	
	@Column(nullable=false, name="numero_conta")
	private String numero_conta;
	
	@Column(nullable=false, name="codigo_banco")
	private int codigo_banco;
	
	public Conta(){}
	
	public Conta(int id, int agencia, String numero_conta, int codigo_banco) {
		super();
		this.id = id;
		this.agencia = agencia;
		this.numero_conta = numero_conta;
		this.codigo_banco = codigo_banco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getNumero_conta() {
		return numero_conta;
	}
	public void setNumero_conta(String numero_conta) {
		this.numero_conta = numero_conta;
	}
	public int getCodigo_banco() {
		return codigo_banco;
	}
	public void setCodigo_banco(int codigo_banco) {
		this.codigo_banco = codigo_banco;
	}
	
	public String toString() {
		return String.format("id: %d\nagencia: %d\nnumero_conta: %s\ncodigo_banco: %d", this.id, this.agencia,this.numero_conta,this.codigo_banco);
	}

}
