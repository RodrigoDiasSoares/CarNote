package br.com.CarNote.activity.model;


import br.com.CarNote.R;

public enum ModelObject {
    tutorialAlcoolOuGasolina(R.string.tutorial_alcool_ou_gasolina, R.layout.view_alcool_ou_gasolina),
    tutorialConsumoPorLitro(R.string.tutorial_consumo_por_litro, R.layout.view_consumo_por_litro),
    tutorialGastosComCarro(R.string.tutorial_gastos_com_carro, R.layout.view_gastos_com_carro);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId){
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getmTitleResId() {
        return mTitleResId;
    }

    public int getmLayoutResId() {
        return mLayoutResId;
    }
}
