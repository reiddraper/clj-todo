(ns todo.lists-resource
  (:use [compojure.core :as compojure :only [ANY routes]])
  (:require compojure.route
            [liberator.core :as liberator]
            [todo.formatters :as formatters]
            [todo.model :as model]))

(defn handle-ok
  [req]
  (let [list-ref (get-in req [:request :state])
        lists (model/get-lists list-ref)
        content-type (get-in req [:representation :media-type])
        json-response (formatters/lists-json-serializer lists)]
    (case content-type
      "application/json" json-response
      "text/html" (formatters/generate-application-template json-response))))

(def resource (liberator/resource :handle-ok handle-ok
                                  :available-media-types ["text/html"
                                                          "application/json"]))
