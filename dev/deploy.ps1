param(
    [string]$Message = "Update"
)

# 1. In das Projekt-Root wechseln
$rootPath = Join-Path $PSScriptRoot ".."
Set-Location $rootPath
Write-Host "Working Directory: $(Get-Location)"

# 2. Git-Workflow erzwingen
Write-Host "Starte Git-Workflow..."
git add -A
git commit -m "$Message"
git push -u origin master

if ($LASTEXITCODE -ne 0) {
    Write-Error "Git Push fehlgeschlagen!"
    exit 1
}

# 3. Deployment auf dem Pi triggern
Write-Host "Starte Deployment auf dem Raspberry Pi..."
ssh bro@192.168.0.128 "~/apollon-core/deploy-backend.sh"

if ($LASTEXITCODE -ne 0) {
    Write-Error "Deployment auf dem Pi fehlgeschlagen."
} else {
    Write-Host "--- Deployment erfolgreich abgeschlossen! ---"
}