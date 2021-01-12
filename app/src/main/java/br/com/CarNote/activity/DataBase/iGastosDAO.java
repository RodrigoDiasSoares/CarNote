package br.com.CarNote.activity.DataBase;

import java.util.List;

import br.com.CarNote.activity.model.Gastos;

public interface iGastosDAO {
    public boolean salvar(Gastos gastos);
    public boolean atualizar(Gastos gastos);
    public boolean deletar(Gastos gastos);
    public List<Gastos> listar();
}
