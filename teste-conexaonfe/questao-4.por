programa {
	funcao inicio() {
	    inteiro salario
	    real taxaReajuste
		
		escreva("Digite seu sal�rio atual: ")
		leia(salario)
		
		escreva("Digite a taxa percentual de reajuste: ")
		leia(taxaReajuste)
		
		salario += salario * (taxaReajuste/100)
		
		escreva("O seu novo sal�rio ser�: ", salario, "\n")
	}
}