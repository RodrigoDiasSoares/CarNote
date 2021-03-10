package br.com.CarNote.activity.model;


import br.com.CarNote.R;

public enum ModelObject {
    tutorialAlcoolOuGasolina(R.string.tutorial_alcool_ou_gasolina, R.layout.tutorial_alcool_ou_gasolina),
    tutorialAlcoolOuGasolinaResultado(R.string.tutorial_alcool_ou_gasolina_resultado,
            R.layout.tutorial_alcool_ou_gasolina_resultado),
    tutorialConsumoPorLitro(R.string.tutorial_consumo_por_litro, R.layout.tutorial_consumo_por_litro),
    TutorialGastosComCarro2(R.string.tutorial_consumo_por_litro_resultado,
            R.layout.tutorial_consumo_por_litro_resultado),
    tutorialGastosComCarro(R.string.tutorial_gastos_com_carro, R.layout.tutorial_gastos_com_carro),
    getTutorialGastosComCarroMenu(R.string.tutorial_gasto_com_o_carro_menu,
            R.layout.tutorial_gastos_com_carro_menu);


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
