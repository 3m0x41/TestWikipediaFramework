import yaml
import subprocess
import os

def execute_command(step):
    description = step.get("description", "No description provided")
    command = step["command"]
    print(f"Executing: {description}")
    try:
        subprocess.run(command, shell=True, check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error executing step: {description}")
        exit(1)

def run_pipeline(file):
    with open(file, "r") as f:
        config = yaml.safe_load(f)
        for step in config["pipeline"]:
            for step_name, step_details in step.items():
                execute_command(step_details)

if __name__ == "__main__":
    # Check for ANDROID_HOME environment variable
    if "ANDROID_HOME" not in os.environ:
        print("ANDROID_HOME is not set. Please configure it before running.")
        exit(1)

    # Run the pipeline
    run_pipeline("pipeline-config.yaml")

    #Install dependencies
    #pip install pyyaml

    #Run YAML Pipeline
    #python pipeline-runner.py