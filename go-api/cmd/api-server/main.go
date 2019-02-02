package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"github.com/unrolled/render"
	"github.com/urfave/negroni"
	"net/http"
)

func main() {
	fmt.Println("Go API Example")
	r := render.New()
	m := mux.NewRouter()

	m.HandleFunc("/healthz", func(w http.ResponseWriter, req *http.Request) {
		r.Text(w, http.StatusOK, "OK")
	})

	n := negroni.Classic()
	n.UseHandler(m)
	fmt.Println("Starting on port 4000")
	n.Run(":4000")
}
