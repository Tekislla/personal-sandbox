package main

import (
	"example/database"
	"example/server"
)

func main() {

	database.StartDB()
	server := server.NewServer()
	server.Run()

}
