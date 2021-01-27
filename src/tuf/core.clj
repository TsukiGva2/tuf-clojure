(ns tuf.core
  (:gen-class))

(defn lifo-into
  "put a vector at the beginning of another vector"
  [target vect]
  (flatten (cons vect target)))

(def PARSE-ERR 1)

(def functions
  #{:print #(println %)
    :push (fn [stack value] (lifo-into stack value))
    })

(defn tufparse
  [expr]
)

(defn tufval
  [expr]
  (tufrun (if (= (tufparse expr) PARSE-ERR)
            #{:print "couldn't parse expression"}
            (tufparse expr))))

(defn -main
  "tuf repl"
  [& args]
  (loop []
    (print "<tuf->")
    (flush)
    (if (= (tufval (read-line)) "exit") nil (recur))))
