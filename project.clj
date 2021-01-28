(defproject tuf "0.1.0"
  :description "the stack stuff programming language"
  :url "https://github.com/TsukiGva2/tuf-clojure"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot tuf.core
  :target-path "bin/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                       :javac-options
                       ["-target" "8"]}})
