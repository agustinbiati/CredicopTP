// Ejemplo de script de inicialización de Jenkins
import jenkins.model.*
import hudson.security.*
import org.jenkinsci.plugins.workflow.job.*

// Crear un usuario administrador
def instance = Jenkins.getInstance()
def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, 'admin')
strategy.add(Jenkins.READ, 'anonymous')
instance.setAuthorizationStrategy(strategy)

// Establecer el administrador
def user = new User("admin")
user.setFullName("Admin User")
user.setPassword("your_password_here")
user.save()

// Instalar plugins desde plugins.txt
def pluginManager = Jenkins.getInstance().getUpdateCenter().getPluginManager()
def plugins = new File("/var/jenkins_home/plugins.txt").readLines()
plugins.each { plugin ->
    pluginManager.install(plugin, true)
}
