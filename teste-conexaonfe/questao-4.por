programa {
	funcao inicio() {
	    inteiro salario
	    real taxaReajuste
		
		escreva("Digite seu salário atual: ")
		leia(salario)
		
		escreva("Digite a taxa percentual de reajuste: ")
		leia(taxaReajuste)
		
		salario += salario * (taxaReajuste/100)
		
		escreva("O seu novo salário será: ", salario, "\n")
	}
}