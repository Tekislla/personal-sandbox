programa {
	funcao inicio() {
		inteiro anos, meses, dias, diasTotal
		
		escreva("Digite quantos anos, meses e dias de vida voc� tem: \n")
		
		escreva("Quantos anos voc� tem?: ")
		leia(anos)
		
		escreva("Quantos meses?: ")
		leia(meses)
		
		escreva("Quantos dias?: ")
		leia(dias)
		
		diasTotal = (anos * 365) + (meses * 30) + dias
		
		escreva("Voc� tem um total de ", diasTotal, " dias de vida!")
	}
}
