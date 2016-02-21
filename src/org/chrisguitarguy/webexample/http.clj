(ns org.chrisguitarguy.webexample.http
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as ringjson]
            [ring.middleware.defaults :as ringdefaults]
            [ring.util.response :as ringutil]
            [compojure.core :as c]
            [com.stuartsierra.component :as component]))


(defrecord JettyServer [port app]
  component/Lifecycle
  (start [self]
    (if (:jettyserver self)
      self
      (assoc self :jettyserver (jetty/run-jetty app {:join? false :port port}))))
  (stop [self]
    (if-let [js (:jettyserver self)]
      (do (.stop js)
          (assoc self :jettyserver nil))
      self)))


(defn new-server [port]
  (map->JettyServer {:port port}))


;; only exists to pull in dependencies. Technically this could all
;; be in `JettyServer`, but that seemed like a weird mix of things.
(defrecord Application [handler]
  clojure.lang.IFn
  (invoke [self request]
    (handler (assoc request :services {})))
  (applyTo [this args] (clojure.lang.AFn/applyToHelper this args)))


(defn new-application []
  (map->Application {}))


(c/defroutes routes
  (c/GET "/" [] (ringutil/response {:message "Hello World"})))


(def bad-json-response {:status 400
                        :headers {"Content-Type" "application/json"}
                        :body {:error "Invalid JSON"}})


(defn new-handler []
  (-> routes
      (ringjson/wrap-json-params {:keywords? true :malformed-response bad-json-response})
      ringjson/wrap-json-response
      (ringdefaults/wrap-defaults ringdefaults/api-defaults)))
