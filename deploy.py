import os
import fileinput
import sys

def replaceAll(file,searchExp,replaceExp):
    for line in fileinput.input(file, inplace=1):
        if searchExp in line:
            line = line.replace(searchExp,replaceExp)
        sys.stdout.write(line)

def runReplace():
    with open(file_path, "r") as open_file:
        for line in open_file:
            if(profile_dev in line):
                replaceAll(file_path, profile_dev, profile_deploy)
            elif(profile_deploy in line):
                replaceAll(file_path, profile_deploy, profile_dev)

path_to_war = "./rhsoft/target/rhsoft.war"
app_name = "teste-rhsoft"
file_path = "./rhsoft/src/main/java/br/com/gabriel/rhsoft/conf/ServletSpringMVC.java"
profile_dev = '"spring.profiles.active", "dev"'
profile_deploy = '"spring.profiles.active", "deploy"'

runReplace()
os.system('cd rhsoft && mvn package')
os.system('heroku plugins:install java')
os.system('heroku deploy:war ' + path_to_war + ' --app ' + app_name)
runReplace()