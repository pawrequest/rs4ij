Work in progress   

IntelliJ plugin for Redscript Modding.  
# Requires:
IntelliJ IDEA Community Edition >= 2024.3.3 (more jetbrains ides to come)
[LSP4IJ](https://github.com/redhat-developer/lsp4ij) intellij plugin from RedHat for LSP integration

# ToDo  

Stage1: mimic/port [vscode_extension](https://github.com/jac3km4/redscript-ide-vscode?tab=readme-ov-file)  

- [x] skeleton project - plugin builds and installs on IDEA Community
- [x] proxy Language server via [LSP4IJ](https://github.com/redhat-developer/lsp4ij) and [redscript-ide](https://github.com/jac3km4/redscript-ide)  
- [x] GoToDefinition / click-thru 
- [ ] Textmate Syntax Highlighting via [redscript syntx highlighting](https://github.com/jackhumbert/redscript-syntax-highlighting)  
- [ ] Debugging via [redscript DAP](https://github.com/jac3km4/redscript-dap)  
- [ ] Integrate  RedHotReload via [RedHotTools](https://github.com/psiberx/cp2077-red-hot-tools)
- [ ] Target multiple IDEs


nb. i dont have a clue what i'm doing. use at your own risk.

to use:  
    - clone and run intellij platform buildPlugin  
OR:  
    - download prebuilt zip from /built  

install zip in IntelliJ IDEA Community Edition >= 2024.3.3 via settings -> plugins -> cog icon -> install plugin from disk  