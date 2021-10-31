package api.v1.contas.resouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.v1.contas.models.Conta;
import api.v1.contas.repository.ContaRepository;

@RestController
@RequestMapping("/api/v1")
public class Controller {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/contas/hello-world")
	public ResponseEntity<String> get() {
		return ResponseEntity.ok("Hello World!"+contaRepository);
	}
	
	//Criacao de conta
	@PostMapping("/contas")
	public ResponseEntity<Conta> salvaConta(@RequestBody Conta conta) {
		//Se a Conta ja existe = BAD_REQUEST
		if (this.contaRepository.existsById(conta.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//ERRO
		}else{
			try {
				this.contaRepository.save(conta);
				return new ResponseEntity<>(HttpStatus.OK);//Conta Criada = SUCCESS
			}catch (Exception e) {
				System.err.println(e);
			}
		}
		//Se nao foi possivel criar a Conta = INTERNAL_SERVER_ERROR
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//ERRO
	}
	
	//Listar contas
	@GetMapping("/contas")
	public ResponseEntity<List<Conta>> listaProdutos(){
		List<Conta> listaContas = this.contaRepository.findAll();
		if (listaContas != null) {
			return new ResponseEntity<List<Conta>>(listaContas, HttpStatus.OK);//SUCCESS
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//ERRO
	}
	
	//Consultar uma conta por id
	@GetMapping("/contas/{id}")
	public ResponseEntity<Conta> findConta(@PathVariable(value="id") int id){
		Conta conta = this.contaRepository.findById(id);
		if (conta != null) {
			return new ResponseEntity<Conta>(conta, HttpStatus.OK);//SUCCESS
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//ERRO
		}
	}
	
	//Atualizar Conta
	@PutMapping("/contas/{id}")
	public ResponseEntity<Conta> atualizarConta(@PathVariable(value="id") int id, @RequestBody Conta updateConta) {
		try {
			//UPDATE Conta SET id=?2, agencia=?3, numero_conta=?4, codigo_banco=?5 WHERE id = ?1
			this.contaRepository.queryUpdate(id,
											 updateConta.getId(),
											 updateConta.getAgencia(),
											 updateConta.getNumero_conta(),
											 updateConta.getCodigo_banco());
		}catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//ERRO
		}
		return new ResponseEntity<>(HttpStatus.OK);//SUCCESS
	}
	
	
	//Deletar Conta
	@DeleteMapping("/contas/{id}")
	public ResponseEntity<Conta> deletarConta(@PathVariable(value="id") int id){
		int affectedRows = this.contaRepository.queryDelete(id);
		if (affectedRows <= 0) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//Erro
		}else {
			return new ResponseEntity<>(HttpStatus.OK);//Conta Deletada
		}	
	}
	
}
