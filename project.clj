(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :plugins [[lein-cljsbuild "0.2.7"]
            [lein-git-deps "0.0.1-SNAPSHOT"]]
  :git-dependencies  [["https://github.com/cmeiklejohn/shafty.git"]]
  :source-paths ["src/clj" "src/cljs" ".lein-git-deps/shafty/src/cljs"]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds {:app {:source-path "src/cljs"
                              :compiler {:output-to "resources/public/application.js"
                                         :optimizations :whitespace
                                         :pretty-print true}}}})
