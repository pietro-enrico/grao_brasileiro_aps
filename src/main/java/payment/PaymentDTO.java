package payment;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

public class PaymentDTO {
    private float valor;
    private String categoria, sub_categoria, quantidade, tipo_pagamento;
    private static StringBuilder errors = new StringBuilder();

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null){
            errors.append("\n - O Campo categoria é obrigatório!" );
        } else {
            this.categoria = categoria.trim();
        }
    }

    public String getSubCategoria() {
        return sub_categoria;
    }

    public void setSubCategoria(String sub_categoria) {
        if(categoria != null && categoria.equals("Dinheiro")) return;

        if (sub_categoria == null) {
            errors.append("\n - O Campo subcategoria é obrigatório!");
        } else {
            this.sub_categoria = sub_categoria.trim();
        }
    }

    public String getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(String quantidade){
        if(categoria != null && categoria.equals("Dinheiro")) return;

        if (quantidade.isEmpty() || quantidade.isBlank() || quantidade == null){
            errors.append("\n - Campo quantidade é obrigatório!");
        } else {
            this.quantidade = quantidade;
        }
    }

    public float getValor(){
        return valor;
    }

    public void setValor(String valor){
        if(categoria != null && !categoria.equals("Dinheiro")) return;

        if (valor.isEmpty() || valor.isBlank() || valor == null){
            errors.append("\n - Campo valor é obrigatório!");
        } else {
            this.valor = Float.parseFloat(valor);
        }
    }

    public String getTipoPagamento() {
        return tipo_pagamento;
    }

    public void setTipoPagamento(Toggle tipo_pagamento){
        if (tipo_pagamento == null){
            errors.append("\n - Por favor, selecione uma opção de pagamento, antes de confirmar a doação!");
        } else {
            String texto = ((ToggleButton) tipo_pagamento).getText();
            this.tipo_pagamento = texto.trim();
        }
    }

    public static StringBuilder getErrors() { return errors; }

    public static void setErrors(String errors) {
        PaymentDTO.errors = new StringBuilder(errors);
    }

}
