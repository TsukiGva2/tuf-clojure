(ns tuf.core
  (:gen-class))

(defn lifo-into
  [target vect]
  (flatten (cons vect target)))



(defn -main
  "tuf repl"
  [& args]
  (print "<tuf->")
  (flush)
  (tufval (read-line)))
