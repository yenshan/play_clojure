(defproject rand-r-circle-anime "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [seesaw "1.4.5"]]
  :main ^:skip-aot rand-r-circle-anime.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
