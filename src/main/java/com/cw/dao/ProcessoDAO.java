package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Processo;
import com.cw.models.Registro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProcessoDAO {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();

    public void inserirProcesso(Processo p) {
        String sql = "INSERT INTO processo (nome, caminho, uso_ram, fk_registro) VALUES (?, ?, ?, ?)";
        con.update(sql, p.getNome(), p.getCaminho(), p.getUsoRam(), p.getFkRegistro());
    }

    public List<Processo> buscarDezProcessosComMaisMemoria(Registro r) {
        String sql = "SELECT * FROM processo WHERE fk_registro = %d ORDER BY uso_ram DESC LIMIT 10".formatted(r.getIdRegistro());

        return con.query(sql, new BeanPropertyRowMapper<>(Processo.class));
    }
}
