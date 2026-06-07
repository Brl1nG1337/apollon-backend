param(
    [string]$Message = "Update"
)

# In das Projekt-Root wechseln
$rootPath = Join-Path $PSScriptRoot ".."
Set-Location $rootPath

Write-Host "Working Directory: $(Get-Location)"

# 1. Alle Änderungen hinzufügen
git add -A

# 2. Prüfen, ob Änderungen existieren
$staged = git diff --cached --name-only
if ($staged -ne "") {
    Write-Host "Änderungen erkannt ($staged), starte Git-Workflow..."

    # 3. Commit durchführen
    git commit -m "$Message"

    # 4. Push durchführen - MIT MEHR INFOS
    git push origin master --verbose

    if ($LASTEXITCODE -ne 0) {
        Write-Error "Git Push fehlgeschlagen!"
        exit 1
    }
} else {
    Write-Host "Keine Änderungen zum Committen gefunden."
}

# 5. Deployment auf dem Pi
Write-Host "Starte Deployment auf dem Raspberry Pi..."
ssh bro@192.168.0.128 "~/apollon-core/deploy-backend.sh"