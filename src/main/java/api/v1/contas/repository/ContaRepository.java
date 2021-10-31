package api.v1.contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import api.v1.contas.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Conta findById(int id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Conta SET id=?2, agencia=?3, numero_conta=?4, codigo_banco=?5 WHERE id = ?1")
    @Transactional
    public int queryUpdate(int idJSON, int id, int agencia, String numero_conta, int codigo_banco);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Conta WHERE id = ?1")
    @Transactional
    public int queryDelete(int id);
}