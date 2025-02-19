programa {
	funcao inicio() {
		inteiro anos, meses, dias, diasTotal
		
		escreva("Digite quantos anos, meses e dias de vida você tem: \n")
		
		escreva("Quantos anos você tem?: ")
		leia(anos)
		
		escreva("Quantos meses?: ")
		leia(meses)
		
		escreva("Quantos dias?: ")
		leia(dias)
		
		diasTotal = (anos * 365) + (meses * 30) + dias
		
		escreva("Você tem um total de ", diasTotal, " dias de vida!")
	}
}
