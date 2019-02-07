package main

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/signalfx/golib/datapoint"
	"github.com/signalfx/golib/sfxclient"
	"github.com/unrolled/render"
	"github.com/urfave/negroni"
	"net/http"
	"os"
)

func main() {
	fmt.Println("Go API Example")
	r := render.New()
	m := mux.NewRouter()
	sfx := sfxclient.NewHTTPSink()
	sfx.AuthToken = os.Getenv("SFX_TOKEN")
	ctx := context.Background()
	dims := make(map[string]string)
	dims["app"] = "k8ws-golang-negroni"

	m.HandleFunc("/healthz", func(w http.ResponseWriter, req *http.Request) {
		_ = sfx.AddDatapoints(ctx, []*datapoint.Datapoint{
			sfxclient.Gauge("k8ws.negroni.path.healthz.calls", dims, 1),
			sfxclient.Cumulative("k8sws.negroni.path.healthz.calls_cumulative", dims, 1),
		})
		_ = r.Text(w, http.StatusOK, "OK")
	})

	n := negroni.Classic()
	n.UseHandler(m)
	fmt.Println("Starting on port 4000")
	n.Run(":4000")
}
