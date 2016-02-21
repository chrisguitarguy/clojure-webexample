(defproject org.chrisguitarguy/webexample "0.1.0-SNAPSHOT"
  :description "Figuring Out to Ring, Compojure, & Component play together"
  :url "https://github.com/chrisguitarguy/clojure-ring-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]]
  :main ^:skip-aot org.chrisguitarguy.webexample
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
