(ns todo.application
  (:require-macros [hiccups.core :as hiccups])
  (:require [hiccups.runtime :as hiccupsrt]
            [shafty.event :as event]
            [shafty.behaviour :as behaviour]))

(defn main []
  "Start the application."

  (.log js/console "Starting the application."))
