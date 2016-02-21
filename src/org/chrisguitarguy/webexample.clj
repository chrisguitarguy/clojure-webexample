(ns org.chrisguitarguy.webexample
  (:require [com.stuartsierra.component :as component]
            [org.chrisguitarguy.webexample.http :as http])
  (:gen-class))

(defn create-system [port]
  (component/system-map
    :handler (http/new-handler)
    :app (component/using
           (http/new-application)
           [:handler])
    :server (component/using
              (http/new-server port)
              [:app])))

(defn -main [& args]
  (let [system (create-system 8888)]
    (component/start system)))
