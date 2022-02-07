# Pestmonitors.com

Pestmonitors.com es una aplicació web d'una sola pàgina (o SPA Single Page Application) per gestionar dispositius de hardware desenvolupats per la indústria/sector del control de plagues. Els dispositius són uns sensors que envien alarmes a l'aplicació quan detecten la presència de rosegadors en un espai monitorat. La pàgina web desenvolupada ha d'administrar tota la gestió d'aquests sensors.</br>

El present document agrupa les diferents parts del projecte software. S'utilitzarà Rational Unified Process (RUP) pel procés de desenvolupament. Es tracta d'un procés dirigit per casos d'usos i centrat en l'arquitectura. Els casos d'usos recullen els requisits funcionals de l'aplicació, i l'arquitectura defineix la manera que s'estructurarà èls elements del software. Aquest procés s'organitza en 5 disciplines:
 * Disciplina de requisists
 * Disciplina d'anàlisis
 * Disciplina de disseny
 * Disciplina d'implementació
 * Disciplina de proves

## DISCIPLINA DE REQUISITS
La disciplina de requisits se centra a cercar actors i els casos d'usos, i també en prioritzar, detallar i estructurar aquests casos d'usos. S'utilitzarà un model del domini per definir les paraules i conceptes generals, així com definir les relacions més rellevants.</br>

### Domain model
![DomainModel](./docs/src/domainModel/domainModel.svg)

### Use Cases
Diagrama de tots els casos d'usos. Un cas d'ús és una secuencia d'accions, incloent-hi variacions, que el sistema pot realitzar i que donen un resultat observable i d'interès a un actor concret.</br>
![UseCases](docs/src/useCaseView/useCases/useCases.svg)

### Use case context
Diagrama on es contextualitzen tots els casos d'usos entre els diferents estats de l'aplicació.</br>
![UseCaseContext](docs/src/useCaseView/context/useCaseContext.svg)

### Use case CreateCompany specification
Diagrama d'especificació del cas d'us de la creació d'una empresa. Definim l'intercanvi d'informació entre l'actor i el sistema per aquest cas d'ús concret, i els camins en que pot derivar aquest intercanvi.</br>
![CreateCompanyUseCaseSpecification](docs/src/useCaseView/specification/createCompany.svg)

### Interface prototype
Prototips d'interfaces i relacions. Example de relació entre la vista principal (main) i la de CreateCompany</br>
![mainToCreateCompany](docs/src/useCaseView/propotype/mainToCreateCompany.svg)


## DISCIPLINA D'ANALISIS
La disciplina d'anàlisis s'analitzà l'arquitectura, els casos d'usos (mitjançant diagrames de colaboració), les clases i els paquets.</br>

### Architecture
Diagrama dels paquets model/vista/controladors.
![PackagesMVC](./docs/src/logicView/analysis/architecture/packages.svg)


### Use cases analysis
Diagrames amb les colaboracions (missatges) entre les vistes, controladors i models. Això brinda traçabilitat a cada cas d'ús, deixen clar quin impacte pot tenir qualsevol canvi al futur.</br>
![collaboration](./docs/src/logicView/analysis/collaboration/createCompany.svg)
