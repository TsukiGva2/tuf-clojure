(ns tuf.core
  (:gen-class))

(defn lifo-into
  "put a vector at the beginning of another vector"
  [target vect]
  (flatten (cons vect target)))

(def PARSE-ERR 1)

(def functions
  {"print" #(println %)
   "push" (fn [stack value] (lifo-into stack value))
   "drop" #(drop 1 %)
   "rot" #(reverse (take 3 %))
   "swap" #(reverse (take 2 %))
   "." #()})

(defn tufparse
  [expr]
)

(defn tufval
  [expr state]
  (tufrun (if (= (tufparse expr) PARSE-ERR)
            {:print "couldn't parse expression"}
            (tufparse expr)) state))

(defn -main
  "tuf repl"
  [& args]
  (loop [state]
    (print "<tuf->")
    (flush)
    (recur (tufval (read-line) state))))
