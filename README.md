# [Perfiz](https://github.com/znsio/perfiz) Practise Exercise

This [practise exercise git branch](https://github.com/znsio/perfiz-demo/tree/practise-exercise) of the [project](https://github.com/znsio/perfiz-demo) gives you an empty template where you try Perfiz from scratch. At the end of this exercise you will have achieved most of what is already setup in the main branch.

## What is already present

An example REST API project and associated Karate API tests.

## What you will learn / setup

* Leveraging the above Karate API tests as Gatling Performance Tests through Perfiz YAML configuration (without writing any Gatling Scala DSL)
* Visualizing the above performance test results through Live Grafana Dashboards (which come pre-configured with Perfiz)
* Monitoring your application performance through Prometheus and Visualizing it on Grafana
* At a high level you will be able to run a sophisticated performance test completely inside Docker without any local setup

## What you will need

* About **5 minutes**
* Docker > 20.10.0
* Your preferred Text Editor / IDE (to edit Perfiz YAML Configuration)

## Instructions

* Running the PetStore REST API
    * Clone this repo and run below command
    ```shell script
    docker compose up -d
    ```
    * This will start a REST API app. You can test this with Curl. This is a sample app against which we will run our performance tests.
    ```shell script
    $ curl http://localhost:9999/pets/1
    {
        "petid": 444
    }
    ```
* Setting up **Perfiz** - Refer to [installation](https://github.com/znsio/perfiz#installation-and-upgrades)
* **Init** Perfiz
    * Make sure you are inside perfiz-demo Dir.
    * Create perfiz specific files inside your project by running the "init" command
    ```shell script
    $PERFIZ_HOME/perfiz.sh init
    ```
    * Observe the files that are added to your project to get you started
        * perfiz.yml
        * perfiz/
            * Grafana Dashboard Json sample
            * Gatling Conf
            * Prometheus YAML
    * Modify the perfiz.yml template and remove the commented YAML content
* **Start** Performance Monitoring Stack
    * Make sure you are inside perfiz-demo Dir. Run below command.
    ```shell script
    $PERFIZ_HOME/perfiz.sh start
    ```
    * Launch Grafana on your browser on localhost:3000. It may ask you to change the password. You can change it or ignore and proceed by re-entering the same username and password.
      * UserName - admin
      * Password - admin
    * On Docker Dashboard you will be able to observe all the containers running under the name "Perfiz"
* **Test** Running Performance Test on the PetStore REST API with Perfiz
    * Make sure you are inside perfiz-demo Dir. Run below command to perform a quick 45 second load test.
    ```shell script
    $PERFIZ_HOME/perfiz.sh test
    ```
    * On Grafana Dashboard (localhost:3000) navigate to ["Perfiz Performance Metric Monitor"](http://localhost:3000/d/4l-HfCPMk/perfiz-performance-metric-monitor) Dashboard
    * Now you should be able to see the performance test metrics in realtime on "Perfiz Performance Metric Monitor" Grafana Dashboard
    ![Grafana Screenshot](https://github.com/znsio/perfiz-demo/blob/main/assets/grafana-test.png)
    * **Congratulations!** You have successfully run a Performance Test on your local machine with little to no setup. To repeat the test you can run "$PERFIZ_HOME/perfiz.sh test" again.
    * If you have another 5 minutes
        * Read through the [explanation](https://github.com/znsio/perfiz-demo#explanation) on how this Demo is working. Then you can play around with the load pattern in perfiz.yml, re-run your Perf Test and observe your changes in Grafana.
        * [Adding prometheus scrape configs and Grafana Dashboards](https://github.com/znsio/perfiz-demo#prometheus-and-grafana-configuration)
* *Stop* Perfiz
  ```shell script
  $PERFIZ_HOME/perfiz.sh stop
  ```
* To stop demo app run below command
  ```shell script
  docker compose down
  ```
  
Refer to [main](https://github.com/znsio/perfiz-demo) branch for expanation.

Thanks for taking the time to try this exercise.
